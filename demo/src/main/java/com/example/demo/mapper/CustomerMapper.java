package com.example.demo.mapper;

import com.example.demo.model.CustomerModel;
import com.example.demo.model.InfoModal;
import com.example.demo.model.request.Forgot;
import com.example.demo.model.request.ForgotUpdate;
import com.example.demo.model.request.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CustomerMapper {
    @Select(value = """
            CALL add_new_customer(
            #{customerID},#{userID},#{password},#{name},#{dateOfBirth},#{address},#{phonenumber},
            #{cardDate},#{cardPlace});
            """)
    int createCustomer(CustomerModel customerModel);
    @Select(value = """
            CALL get_all_customer();
            """)
    List<CustomerModel> getAllcustomer();
    @Select(value = """
            CALL delete_customer( #{customerID});
            """)
    int deleteCustomer( int CustomerID);
    @Select(value = """
            CALL get_customer_by_user(#{userID})
            """)
    List<CustomerModel> getCustomerbyID(int userID);
    @Select(value = """
            CALL customer_update(#{customerID},#{name},#{dateOfBirth},#{address},#{phonenumber})
            """)
    int update_info(InfoModal infoModal);
    @Select(value = """
            CALL check_forgot_password(#{customerID},#{phonenumber});
            """)
    int check_forgot_password(Forgot forgot);
    @Select(value = """
            CALL forgot_password_update(#{customerID},#{password});
            """)
    int forgot_password_update(ForgotUpdate forgotUpdate);
}
