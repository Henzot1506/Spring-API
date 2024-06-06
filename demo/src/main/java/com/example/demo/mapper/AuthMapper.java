package com.example.demo.mapper;

import com.example.demo.model.AdminModel;
import com.example.demo.model.UserModel;
import com.example.demo.model.request.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AuthMapper {
    @Select( """
            CALL choose_user(#{username},#{password});
            """)
    UserModel loginUser(User user);
    @Select("""
            CALL get_admin(#{username},#{password})
            """)
    UserModel adminLogin(User loginAdmin);
}
