package ch.zli.m223.punchclock.service;

import ch.zli.m223.punchclock.domain.AppUser;
import ch.zli.m223.punchclock.domain.Entry;
import ch.zli.m223.punchclock.repository.EntryRepository;
import ch.zli.m223.punchclock.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public AppUser createUser(AppUser user) {
        return userRepository.saveAndFlush(user);
    }

    public List<AppUser> findAll() {
        return userRepository.findAll();
    }

    public void deleteEntry(Long id) {
        var entry = userRepository.findById(id).orElseThrow();
        userRepository.delete(entry);
    }
}
