package edu.fdzc.service;

import edu.fdzc.entity.User;

public interface IUserService {
    User checkUser(String username,String password);
}
