package com.tms.security;

//@Service
//public class CustomUserDetailService implements UserDetailsService {
//private final UserRepository userRepository;

//@Autowired
//public CustomUserDetailService(UserRepository userRepository) {
//  this.userRepository = userRepository;
//}

//   @Override
// public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//   User user = userRepository.findUserByLogin(username).orElseThrow(() -> new UsernameNotFoundException(username));
// UserDetails securityUser = org.springframework.security.core.userdetails.User
//    .builder().username(user.getLogin())
//      .password(user.getPassword())
//        .roles(user.getRole())
//          .build();
//    return securityUser;
//  }
//}
