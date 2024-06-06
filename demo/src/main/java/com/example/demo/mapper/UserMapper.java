package com.example.demo.mapper;

import com.example.demo.model.InfoModal;
import com.example.demo.model.UserModel;
import com.example.demo.model.request.UpdatePassword;
import com.example.demo.model.request.User;
import com.example.demo.model.response.UserInfo;
import com.example.demo.model.response.UserResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select(value = """
            CALL get_all_user(#{userID});
            """)
    List<UserInfo> getAllUser(int userID);
    @Select(value = """
            CALL add_new_user(#{username},#{password});
            """)
    int createUser(User User);
    @Select(value = """
            CALL update_password(#{userID},#{password},#{newpassword});
            """)
    int update_password(UpdatePassword updatePassword);
}
