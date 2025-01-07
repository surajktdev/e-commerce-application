package com.ecommerce.module.user.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecommerce.entity.User;
import com.ecommerce.exceptions.ResourceNotFoundException;
import com.ecommerce.repository.UserRepository;
import com.ecommerce.module.user.value.UserRequest;
import com.ecommerce.module.user.value.UserResponse;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class UserOperations {

        private final UserRepository userRepository;
        private final PasswordEncoder passwordEncoder;

        // @Transactional
        public UserResponse registerNewUser(UserRequest request){


            Long lastMemberId = userRepository.findMemberId();
            User userData = new User();
            userData.setMemberId(lastMemberId == null ? 101 : lastMemberId + 1);
           userData.setName(request.name());
           userData.setEmail(request.email());
           userData.setPassword(passwordEncoder.encode(request.password()));
           userData.setRole(request.role());

            User save = userRepository.save(userData);
            return userResponse(save);
        }


        public UserResponse updateUserDetails(Long id, UserRequest request){
            User user = userRepository.findById(Math.toIntExact(id)).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
            user.setName(request.name());
            user.setEmail(request.email());
            user.setPassword(passwordEncoder.encode(request.password()));
            user.setRole(request.role());

            User updatedUser = userRepository.save(user);
            return userResponse(updatedUser);
        }

        public UserResponse getUser(Long id){
            User user = userRepository.findById(Math.toIntExact(id)).orElseThrow(()-> new ResourceNotFoundException("User", "id", id));
            return userResponse(user);

        }

        public List<UserResponse> getAllUser(){
            List<User> allUserDetails = (List<User>) userRepository.findAll();
            return allUserDetails.stream().map(user -> userResponse(user)).toList();

        }


        public void deleteUser(Long id){
            User user = userRepository.findById(Math.toIntExact(id)).orElseThrow(()-> new ResourceNotFoundException("User", "id", id));
            userRepository.delete(user);
        }

        public UserResponse deactivateUser(Long userId) {
            User user = userRepository.findById(Math.toIntExact(userId))
                .orElseThrow(() -> new RuntimeException("User not found"));
            user.setStatus("DEACTIVATED");
            User save = userRepository.save(user);

            return UserResponse.builder().email(save.getEmail()).status(save.getStatus()).build();
        }

        public UserResponse activateUser(Long userId) {
            User user = userRepository.findById(Math.toIntExact(userId))
                .orElseThrow(() -> new RuntimeException("User not found"));
            user.setStatus("ACTIVE");
            User save = userRepository.save(user);

            return UserResponse.builder().email(save.getEmail()).status(save.getStatus()).build();
        }
        
        private UserResponse userResponse(User user){
            return UserResponse.builder().id(user.getId()).memberId(user.getMemberId()).name(user.getName()).email(user.getEmail()).role(user.getRole()).build();
        }
}
