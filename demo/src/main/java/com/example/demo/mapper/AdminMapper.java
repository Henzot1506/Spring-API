package com.example.demo.mapper;

import com.example.demo.model.AdminModel;
import com.example.demo.model.DepositModel;
import com.example.demo.model.StatusModel;
import com.example.demo.model.UserModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdminMapper {

    @Select("""
            CALL deposit_approval(#{transactionID},#{status},#{userID})
            """)
    int depositApproval(DepositModel depositModel);
    @Select("""
            CALL active_user(#{userID},#{isActive})
            """)
    int active_user(StatusModel statusModel);
    @Select("""
            CALL delete_user(#{userID})
            """)
    int deleteUser(int userID);
}
