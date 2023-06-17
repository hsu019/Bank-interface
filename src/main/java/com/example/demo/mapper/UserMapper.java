package com.example.demo.mapper;


import com.example.demo.models.Role;
import com.example.demo.models.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserMapper {

    @Select("select * from tb_user where username=#{username}")
    User loadUserByUsername(String username);

    @Select("select * from " +
            "tb_role r, tb_user_role ur " +
            "where r.id=ur.rid and ur.uid=#{id}")
    List<Role> getUserRolesByUid(Integer id);

    @Select("select * from tb_user where token=#{token}")
    User selectUserFromToken(String token);

    @Select("select * from " +
            "tb_user, tb_card " +
            "where tb_user.id=tb_card.user_id and tb_user.id=#{id}")
    List<String> selectCardFromUserId(int id);
}
