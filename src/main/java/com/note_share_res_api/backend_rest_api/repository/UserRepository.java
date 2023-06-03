package com.note_share_res_api.backend_rest_api.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.note_share_res_api.backend_rest_api.modesl.*;

public interface UserRepository extends JpaRepository<User, Integer> {

}