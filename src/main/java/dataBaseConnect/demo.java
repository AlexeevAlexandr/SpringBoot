package dataBaseConnect;

import java.util.ArrayList;
import java.util.List;

public class demo {
    public static void main(String[] args) {
        ArrayList list = new ArrayList();
        ArrayList list1 = (ArrayList) Commands.list();
        for (Object list2 : list1) {
            list.add(list2);
        }
        System.out.println(list.get(1));

    }
}
