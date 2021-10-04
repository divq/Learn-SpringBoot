package com.example.mybatis_plus.mp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mybatis_plus.mp.entity.User;
import com.example.mybatis_plus.mp.mapper.UserMapper;
import com.example.mybatis_plus.mp.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author idivq
 * @since 2021-10-04
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
