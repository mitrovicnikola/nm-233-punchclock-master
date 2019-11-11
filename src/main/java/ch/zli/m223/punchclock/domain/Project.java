package ch.zli.m223.punchclock.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private String name;

    public List<Entry> getProjects() {
        return projects;
    }

    public void setProjects(List<Entry> entries) {
        this.projects = entries;
    }

    @OneToMany
    @JoinColumn(name = "entry")
    private List<Entry> projects = new ArrayList<>();

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
