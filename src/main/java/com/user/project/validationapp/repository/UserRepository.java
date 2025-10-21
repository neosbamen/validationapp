package com.user.project.validationapp.repository;

import com.user.project.validationapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
