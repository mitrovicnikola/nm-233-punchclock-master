package ch.zli.m223.punchclock.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private String name;

    public List<Entry> getActivities() {
        return entries;
    }

    public void setActivities(List<Entry> entries) {
        this.entries = entries;
    }

    @OneToMany
    @JoinColumn(name = "entry")
    private List<Entry> entries = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String username) {
        this.name = name;
    }
}
