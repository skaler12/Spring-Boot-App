package com.spring.boot.application.service;

import com.spring.boot.application.UsernameOrIdNotFound;
import com.spring.boot.application.dto.ChangePasswordForm;
import com.spring.boot.application.entity.User;
import com.spring.boot.application.exception.CustomeFieldValidationException;
import com.spring.boot.application.repository.RoleRepository;
import com.spring.boot.application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    //metoda zwracajaca wszystkich urzytkownikow
    @Override
    public Iterable<User> getAllUsers() {

        return userRepository.findAll();
    }

    //sprawdzam czy user o takim username juz istnieje
    private boolean checkUsernameAvailable(User user) throws Exception {
        Optional<User> userFound = userRepository.findByUsername(user.getUsername());
        if (userFound.isPresent()) {
            throw new CustomeFieldValidationException("Username no disponible","username");
        }
        return true;
    }

    //sprawdza czy haslo jest takie samo jak potwierdzenie hasla , jezeli nie to rzuca wyjatek
    private boolean checkPasswordValid(User user) throws Exception {
        if (user.getConfirmPassword() == null || user.getConfirmPassword().isEmpty()) {
            throw new CustomeFieldValidationException("Confirm Password es obligatorio","confirmPassword");
        }

        if (!user.getPassword().equals(user.getConfirmPassword())) {
            throw new CustomeFieldValidationException("Password y Confirm Password no son iguales","password");
        }
        return true;
    }

    //zapisanie usera po spelnieniu warunkow
    @Override
    public User createUser(User user) throws Exception {
        if (checkUsernameAvailable(user) && checkPasswordValid(user)) {
            String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
            user = userRepository.save(user);
        }
        return user;
    }

    //znajdz usera po id
    @Override
    public User getUserById(Long id) throws UsernameOrIdNotFound {
        return userRepository.findById(id).orElseThrow(() -> new UsernameOrIdNotFound("El Id del usuario no existe."));
    }

    //update user
    @Override
    public User updateUser(User fromUser) throws Exception {
        User toUser = getUserById(fromUser.getId());
        mapUser(fromUser, toUser);
        return userRepository.save(toUser);
    }

    /**
     * Map everythin but the password.
     *
     * @param from
     * @param to
     */
    //mapa pozwalajaca ustalic , zaktualizowac wartosci dla usera , metoda uzywana w metodzie udpateUser
    protected void mapUser(User from, User to) {
        to.setUsername(from.getUsername());
        to.setFirstName(from.getFirstName());
        to.setLastName(from.getLastName());
        to.setEmail(from.getEmail());
        to.setRoles(from.getRoles());
    }

    //metoda usuwajaca usera
    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public void deleteUser(Long id) throws UsernameOrIdNotFound {
        User user = getUserById(id);
        userRepository.delete(user);
    }

    //metoda do zmiany hasla
    @Override
    public User changePassword(ChangePasswordForm form) throws Exception {
        User user = getUserById(form.getId());

        if (!isLoggedUserADMIN() && !user.getPassword().equals(form.getCurrentPassword())) {
            throw new Exception("Current password uncorrect.");
        }

        if (user.getPassword().equals(form.getNewPassword())) {
            throw new Exception("Change password unaveiable, new password is the same how actually password");
        }

        if (!form.getNewPassword().equals(form.getConfirmPassword())) {
            throw new Exception("Mistake ! please confirm password correct ! ");
        }

        String encodePassword = bCryptPasswordEncoder.encode(form.getNewPassword());
        user.setPassword(encodePassword);
        return userRepository.save(user);
    }

    private boolean isLoggedUserADMIN() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails loggedUser = null;
        Object roles = null;
        if (principal instanceof UserDetails) {
            loggedUser = (UserDetails) principal;
            roles = loggedUser.getAuthorities().stream()
                    .filter(x -> "ROLE_ADMIN".equals(x.getAuthority())).findFirst()
                    .orElse(null);
        }
        return roles != null ? true : false;
    }

    private User getLoggedUser() throws Exception {
        //Obtener el usuario logeado
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        UserDetails loggedUser = null;

        //Verificar que ese objeto traido de sesion es el usuario
        if (principal instanceof UserDetails) {
            loggedUser = (UserDetails) principal;
        }
        User myUser = userRepository
                .findByUsername(loggedUser.getUsername()).orElseThrow(() -> new Exception("Error obteniendo el usuario logeado desde la sesion."));

        return myUser;
    }
}


