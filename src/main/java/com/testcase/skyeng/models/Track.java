package com.testcase.skyeng.models;

import com.testcase.skyeng.models.additions.CommonEntity;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//Track entity for storing data about path of package.
@Entity
public class Track extends CommonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private MailPackage mailPackage;
    @ManyToMany
    private final List<PostOffice> path;
    private int currentState;
    private boolean arrived;

    public Track() {
        this(null, null, new ArrayList<>());
    }

    public Track(MailPackage mailPackage) {
        this.mailPackage = mailPackage;
        this.path = new ArrayList<>();
        this.currentState = 0;
        this.arrived = false;
    }

    public Track(Long id, MailPackage mailPackage, List<PostOffice> path) {
        this.id = id;
        this.mailPackage = mailPackage;
        this.path = path;
        this.currentState = 0;
        this.arrived = false;
    }

    public Track(MailPackage mailPackage, List<PostOffice> path) {
        this.mailPackage = mailPackage;
        this.path = path;
        this.currentState = 0;
        this.arrived = false;
    }

    @Override
    public <T> void copy(T item) {
        //not implemented;
    }

    public void setFirstOffice(PostOffice office){
        this.path.add(0, office);
    }

    /**
     * Add single PostOffice to distinct position in path.
     *
     * @param from  position after which to add
     * @param toAdd office to add
     */
    public void addPostOfficeAfter(PostOffice from, PostOffice toAdd) {
        int indexFrom = path.indexOf(from);
        path.add(indexFrom + 1, toAdd);
    }

    /**
     * Add list of PostOffices to distinct position in path.
     *
     * @param from  position after which to add
     * @param toAdd list to add
     */

    public void addPostOfficeAfter(PostOffice from, List<PostOffice> toAdd) {
        int indexFrom = path.indexOf(from);
        path.addAll(indexFrom + 1, toAdd);
    }

    /**
     * Get current position of package.
     *
     * @return PostOffice where package is now.
     */
    public PostOffice getCurrentOffice() {
        if (path.size() != 0) {
            return path.get(currentState);
        } else return null;
    }

    /**
     * Move package to next step on the way.
     */
    public void moveToNextStep() {
        int allSteps = path.size();
        currentState++;
        if (currentState == allSteps) {
            arrived = true;
        }
        if (currentState > allSteps) {
            throw new RuntimeException("Error in steps count!");
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MailPackage getMailPackage() {
        return mailPackage;
    }

    public void setMailPackage(MailPackage mailPackage) {
        this.mailPackage = mailPackage;
    }

    public List<PostOffice> getPath() {
        return path;
    }

    public int getCurrentState() {
        return currentState;
    }

    public boolean isArrived() {
        return arrived;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Track track)) return false;
        return currentState == track.currentState && arrived == track.arrived && Objects.equals(mailPackage, track.mailPackage) && Objects.equals(path, track.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mailPackage, path, currentState, arrived);
    }
}
