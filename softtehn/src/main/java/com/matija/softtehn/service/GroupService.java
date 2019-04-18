package com.matija.softtehn.service;

import com.matija.softtehn.model.Group;
import com.matija.softtehn.model.User;
import com.matija.softtehn.model.UserPrincipal;
import com.matija.softtehn.repository.GroupRepository;
import com.matija.softtehn.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserRepository userRepository;

    public void addUser(UserPrincipal userPrincipal, User userToAdd) {
        User authUser = userPrincipal.getUser();
        Group group = authUser.getGroup();
        userToAdd.setGroup(group);
        userRepository.save(userToAdd);
    }
}
