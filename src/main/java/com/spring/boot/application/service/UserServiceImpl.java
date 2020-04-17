package com.spring.boot.application.service;

import com.spring.boot.application.entity.User;
import com.spring.boot.application.repository.RoleRepository;
import com.spring.boot.application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;

    @Override
    public Iterable<User>getAllUsers(){
        return userRepository.findAll();
    }
    //sprawdzam czy user o takim username juz istnieje
    private boolean checkUsernameAvailable(User user) throws Exception {
        Optional<User> userFound = userRepository.findByUsername(user.getUsername());
        if (userFound.isPresent()) {
            throw new Exception("Username no avaible");
        }
        return true;
    }
//sprawdza czy haslo jest takie samo jak potwierdzenie hasla , jezeli nie to rzuca wyjatek
    private boolean checkPasswordValid(User user) throws Exception {
        if (user.getConfirmPassword() == null || user.getConfirmPassword().isEmpty()) {
            throw new Exception("Confirm Password is request");
        }

        if ( !user.getPassword().equals(user.getConfirmPassword())) {
            throw new Exception("Password no confirm");
        }
        return true;
    }

   //zapisanie usera po spelnieniu warunkow
    @Override
    public User createUser(User user) throws Exception {
        if (checkUsernameAvailable(user) && checkPasswordValid(user)) {
            user = userRepository.save(user);
        }
        return user;
    }
    @Override
    public User getUserById(Long id) throws Exception {
        return userRepository.findById(id).orElseThrow(() -> new Exception("El usuario para editar no existe."));
    }

    @Override
    public User updateUser(User fromUser) throws Exception {
        User toUser = getUserById(fromUser.getId());
        mapUser(fromUser, toUser);
        return userRepository.save(toUser);
    }

    /**
     * Map everythin but the password.
     * @param from
     * @param to
     */
    protected void mapUser(User from,User to) {
        to.setUsername(from.getUsername());
        to.setFirstName(from.getFirstName());
        to.setLastName(from.getLastName());
        to.setEmail(from.getEmail());
        to.setRoles(from.getRoles());
    }
    @Override
    public void deleteUser(Long id) throws Exception {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new Exception("UsernotFound in deleteUser -"+this.getClass().getName()));

        userRepository.delete(user);
    }
}
