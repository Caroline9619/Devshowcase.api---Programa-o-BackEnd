package br.com.csm.devshowcase.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, length = 500)
    private String description;

    @Column(nullable = false)
    private String repositoryUrl;

    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @ManyToMany
    @JoinTable(
            name = "project_technology",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "technology_id")
    )
    private List<Technology> technologies = new ArrayList<>();

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Feedback> feedbacks = new ArrayList<>();

    @Column(nullable = false)
    private Integer likes = 0;

    @Column(nullable = false)
    private Double averageRating = 0.0;

    public Project() {}

    public Project(String title, String description, String repositoryUrl) {
        this.title = title;
        this.description = description;
        this.repositoryUrl = repositoryUrl;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getRepositoryUrl() {
        return repositoryUrl;
    }

    public Profile getProfile() {
        return profile;
    }

    public List<Technology> getTechnologies() {
        return technologies;
    }

    public List<Feedback> getFeedbacks() {
        return feedbacks;
    }

    public Integer getLikes() {
        return likes;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRepositoryUrl(String repositoryUrl) {
        this.repositoryUrl = repositoryUrl;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public void setTechnologies(List<Technology> technologies) {
        this.technologies = technologies;
    }

    public void setFeedbacks(List<Feedback> feedbacks) {
        this.feedbacks = feedbacks;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }
}