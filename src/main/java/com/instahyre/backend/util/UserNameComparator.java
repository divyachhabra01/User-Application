package com.instahyre.backend.util;

import com.instahyre.backend.dto.UserDTO;
import lombok.AllArgsConstructor;

import java.util.Comparator;

@AllArgsConstructor
public class UserNameComparator implements Comparator<UserDTO> {
    private String userNameQuery;

    @Override
    public int compare(UserDTO o1, UserDTO o2) {
        return longestMatchingPrefix(o2) - longestMatchingPrefix(o1);
    }

    private int longestMatchingPrefix(UserDTO user) {
        final String userName = user.getUserName();
        int maxMatchCount = 0;
        for (int i = 0; i < Math.min(userName.length(), userNameQuery.length());i++) {
            if (userName.charAt(i) == userNameQuery.charAt(i))
                maxMatchCount++;
        }

        return maxMatchCount;
    }
}
