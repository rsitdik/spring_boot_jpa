package com.itvdn.controller;

import com.itvdn.model.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Iterator;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UnitTest {
    private static final Log LOG = LogFactory.getLog(UnitTest.class);

    @Autowired
    public List<User> userList;

    @Autowired
    @Qualifier(value = "friend1")
    private User user;

    @Test
    public void testUser() {
        System.out.println(user.getName());
        Assert.assertEquals("Oleg", user.getName());
    }

    @Test
    public void testFriendship() {
        System.out.println("******************");
        System.out.println("info about friends");
        Iterator<User> it = userList.iterator();
        it.next();
        while (it.hasNext()) {
            User tempUser = it.next();
            if (it.hasNext()) {
                System.out.println(User.makeFriends(tempUser, it.next()));
            }
        }
    }

    @Test
    public void testFriendship2() {
        LOG.info("******************");
        LOG.info("info about friends");
        Iterator<User> it = userList.iterator();
        it.next();
        while (it.hasNext()) {
            User tempUser = it.next();
            if (it.hasNext()) {
                LOG.info(User.makeFriends(tempUser, it.next()));
            }
        }
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testUser2() {
        User.getUserName();
    }

//    @Test(timeout = 500)
//    public void testUser3() {
//        User.waitInQueue();
//    }

    @Test(timeout = 1500)
    public void testUser4() {
        User.waitInQueue();
    }
}
