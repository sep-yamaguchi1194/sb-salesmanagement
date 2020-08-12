package sep.salesmanagement.yt.roles;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class UserRolesUtil {

  private static Function<String, String> roleLookup = (role -> {
    String tmp = role.trim().toUpperCase();
    return UserRoles.lookup(tmp).getValue();
  });

  // String[]→カンマ区切りのString
  public static String joining(String[] roles) {
    if (roles == null || roles.length == 0) {
      return null;
    }
    return Arrays.stream(roles)
        .map(roleLookup)
        .collect(Collectors.joining(","));
  }

  //カンマ区切りのString→String[]
  public static String[] toArray(String joinedRoles) {
    if (joinedRoles == null || joinedRoles.isEmpty()) {
      return null;
    }
    return Arrays.stream(joinedRoles.split(","))
        .map(roleLookup)
        .toArray(String[]::new);
  }

  //カンマ区切りのString→Set型
  public static Set<GrantedAuthority> toSet(String joinedRoles) {
    if (joinedRoles == null || joinedRoles.isEmpty()) {
      return Collections.emptySet();
    }
    return Arrays.stream(joinedRoles.split(","))
        .map(roleLookup)
        .map(SimpleGrantedAuthority::new)
        .collect(Collectors.toSet());
  }

  //Collection型→String[]
  public static String[] toArray(Collection<? extends GrantedAuthority> authorities) {
    return authorities.stream()
        .map(GrantedAuthority::getAuthority)
        .toArray(String[]::new);
  }

}