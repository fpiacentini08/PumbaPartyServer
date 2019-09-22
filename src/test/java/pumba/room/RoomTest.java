package test.java.pumba.room;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import main.java.pumba.rooms.Room;
import main.java.pumba.users.User;
import test.java.pumba.users.UserFixture;

public class RoomTest
{

	private static final User user1 = new User("test1", "test1");
	private static final User user2 = new User("test2", "test2");
	private static final User user3 = new User("test3", "test3");
	private static final User user4 = new User("test4", "test4");
	private static final User user5 = new User("test5", "test5");
	private static final User user6 = new User("test6", "test6");
	
	@Test
	public void roomConstructorTest()
	{
		Room room = RoomFixture.withDefaults();
		assertNotNull(room);
		assertNotNull(room.getId());
		assertNotNull(room.getMaster());
		assertNotNull(room.getPlaying());
		assertNotNull(room.getUsers());

		assertEquals(UserFixture.withDefaults(), room.getMaster());
		assertFalse(room.getPlaying());

		assertEquals(1, room.getUsers().size(), 0);
		assertTrue(room.getUsers().contains(UserFixture.withDefaults()));
	}
	
	@Test
	public void roomEnterTest() {
		Room room = RoomFixture.withDefaults();
		assertTrue(room.enter(UserFixture.withDefaults()));

		assertTrue(room.enter(user1));
		assertTrue(room.enter(user2));
		assertTrue(room.enter(user3));
		assertTrue(room.enter(user4));
		assertFalse(room.enter(user5));
		assertFalse(room.enter(user6));
		assertTrue(room.getUsers().contains(user1));
		assertTrue(room.getUsers().contains(user2));
		assertTrue(room.getUsers().contains(user3));
		assertTrue(room.getUsers().contains(user4));
		assertFalse(room.getUsers().contains(user5));
		assertFalse(room.getUsers().contains(user6));
		
		assertTrue(room.getUsers().contains(room.getMaster()));
		assertEquals(RoomFixture.master, room.getMaster());

	}
	
	@Test
	public void roomEnterAndExitTest() {
		Room room = RoomFixture.withDefaults();
		assertTrue(room.enter(UserFixture.withDefaults()));

		assertTrue(room.enter(user1));
		assertTrue(room.enter(user2));
		assertTrue(room.enter(user3));
		assertTrue(room.enter(user4));

		room.exit(user1);
		assertFalse(room.getUsers().contains(user1));
		assertEquals(RoomFixture.master, room.getMaster());
		
		room.exit(user2);
		assertFalse(room.getUsers().contains(user2));
		assertEquals(RoomFixture.master, room.getMaster());
		assertEquals(3, room.getUsers().size(), 0);

		room.exit(room.getMaster());
		assertNotNull(room.getMaster());
		assertFalse(room.getUsers().contains(UserFixture.withDefaults()));
		assertFalse(room.getMaster().equals(RoomFixture.master));
		
	}
	
	@Test
	public void roomEnterAndExitRoomFullAndEmptyTest() {
		Room room = RoomFixture.withDefaults();
		assertTrue(room.enter(UserFixture.withDefaults()));

		assertTrue(room.enter(user1));
		assertTrue(room.enter(user2));
		assertTrue(room.enter(user3));
		assertTrue(room.enter(user4));
		
		room.exit(room.getMaster());

		assertNotNull(room.getMaster());
		assertFalse(room.getMaster().equals(RoomFixture.master));
		assertFalse(room.getUsers().contains(RoomFixture.master));
		
		room.exit(user1);
		assertNotNull(room.getMaster());

		room.exit(user2);
		assertNotNull(room.getMaster());
		
		room.exit(user3);
		assertNotNull(room.getMaster());
		
		room.exit(user4);
		assertNull(room.getMaster());
		
	}
}
