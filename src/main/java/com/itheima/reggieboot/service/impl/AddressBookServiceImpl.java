package com.itheima.reggieboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import com.itheima.reggieboot.entity.AddressBook;
import com.itheima.reggieboot.mapper.AddressBookMapper;
import com.itheima.reggieboot.service.AddressBookService;
import org.springframework.stereotype.Service;

@Service
public class AddressBookServiceImpl extends ServiceImpl<AddressBookMapper, AddressBook> implements AddressBookService {

}
