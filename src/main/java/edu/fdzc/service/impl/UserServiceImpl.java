package edu.fdzc.service.impl;

import edu.fdzc.entity.User;
import edu.fdzc.mapper.IUserMapper;
import edu.fdzc.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserMapper userMapper;

    /**
     * DigestUtils.md5DigestAsHex：Spring内置加密工具
     */
    @Transactional
    @Override
    public User checkUser(String username, String password) {
        User user = userMapper.findByUsernameAndPassword(username, DigestUtils.md5DigestAsHex(password.getBytes()));
        return user;
    }
}
