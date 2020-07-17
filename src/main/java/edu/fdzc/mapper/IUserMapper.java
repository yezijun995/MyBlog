package edu.fdzc.mapper;

import edu.fdzc.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 用户持久层
 */
@Mapper
public interface IUserMapper {

    User findByUsernameAndPassword(@Param("username") String username,@Param("password") String password);
}
