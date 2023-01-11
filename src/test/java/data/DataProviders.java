package data;

import org.testng.annotations.DataProvider;

public class DataProviders {

    @DataProvider(name = "nav buttons")
    public Object[] data(){
        Object[] navBtns = new String[] {"Home", "Curriculum", "Notes", "Inputs", "Selectors",
                "Select-class", "Alert", "Pop-Up", "Multiple-window", "Tables", "Calendar", "iFrames",
                "Action-class", "JS-Executor", "Synchronization", "Files", "User-Mgt", "Others"};

        return navBtns;
    }

    @DataProvider(name = "roles")
    public Object[] data2(){
        Object[] roles = new String[] {"Mentor", "Student", "Instructor"};
        return roles;
    }
}
