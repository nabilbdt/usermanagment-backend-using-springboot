package com.user.backendspringbootapp.repositories;

import com.user.backendspringbootapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long> {

}
