package com.marcosbarbero.so.multiple.ds.repository.user;

import com.marcosbarbero.so.multiple.ds.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
