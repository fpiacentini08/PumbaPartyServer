package test.java.pumba.users;

import java.util.UUID;

import main.java.pumba.users.User;

public class UserFixture
{
    public static String username = "user.test";
    public static String password = "1q2w3e4r";
    public static UUID roomId = UUID.randomUUID();
    

    private User build()
    {
        User user = new User(username, password);
        user.setRoomId(roomId);
        return user;
    }

    public static User withDefaults()
    {
        return new UserFixture().build();
    }
}
