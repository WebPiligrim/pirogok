package com.rybak.education.pirogok.repo;

import com.rybak.education.pirogok.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    User findByUsername(String username);
}
