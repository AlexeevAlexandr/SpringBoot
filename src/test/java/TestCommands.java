import dataBaseConnect.Commands;
import hello.MainController;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class TestCommands {
    private Commands commands = new Commands();
    private MainController controller = new MainController();

    @Test
    public void addTest(){
        commands.clear();
        String date = controller.getDateTime();
        commands.add("FirstName", "LastName", "email@i.ua", date);
        List list = commands.list();
        assertEquals(list.toString(),
                "[ID: 1\n" +
                "First Name: FirstName\n" +
                "Last Name: LastName\n" +
                "Email: email@i.ua\n" +
                "Date of registration: " + date + "]");
    }

    @Test
    public void clearTest(){
        commands.add("FirstName", "LastName", "email@i.ua", controller.getDateTime());
        commands.clear();
        List list = commands.list();
        assertEquals(list.toString(),"[]");
    }

    @Test
    public void findByIdTest(){
        commands.clear();
        String date = controller.getDateTime();
        commands.add("FirstName1", "LastName1", "email_1@i.ua", controller.getDateTime());
        commands.add("FirstName2", "LastName2", "email_2@i.ua", date);
        commands.add("FirstName3", "LastName3", "email_3@i.ua", controller.getDateTime());
        List list = commands.user(2);
        assertEquals(list.toString(),
                "[ID: 2\n" +
                        "First Name: FirstName2\n" +
                        "Last Name: LastName2\n" +
                        "Email: email_2@i.ua\n" +
                        "Date of registration: " + date + "]");
    }

    @Test(expected = IllegalStateException.class)
    public void incorrectEmailTest(){
        commands.add("FirstName", "LastName", "incorrectEmail", controller.getDateTime());
    }

    @Test
    public void test() {
        List mockedList = mock(List.class);

        mockedList.add("three");
        mockedList.add("one");
        mockedList.add("three");
        mockedList.add("one");
        mockedList.clear();

        verify(mockedList, times(2)).add("one");
        verify(mockedList, never()).add("never");
        verify(mockedList).clear();
        verify(mockedList, atLeastOnce()).add("three");
        verify(mockedList, atLeast(2)).add("three");
        verify(mockedList, atMost(5)).add("three");
    }
}
