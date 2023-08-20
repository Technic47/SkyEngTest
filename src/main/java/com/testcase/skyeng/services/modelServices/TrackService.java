package com.testcase.skyeng.services.modelServices;

import com.testcase.skyeng.exceptions.ResourceNotFoundException;
import com.testcase.skyeng.models.MailPackage;
import com.testcase.skyeng.models.PostOffice;
import com.testcase.skyeng.models.Track;
import com.testcase.skyeng.repositories.TrackRepository;
import com.testcase.skyeng.services.CommonService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrackService extends CommonService<Track, TrackRepository> {
    private final PostOfficeService postOfficeService;
    private final MailPackageService mailPackageService;

    public TrackService(TrackRepository repository, PostOfficeService postOfficeService, MailPackageService mailPackageService) {
        super(repository);
        this.postOfficeService = postOfficeService;
        this.mailPackageService = mailPackageService;
    }

    public Track addPackage(Long trackId, Long packageId) throws ResourceNotFoundException {
        Optional<Track> findTrack = repository.findById(trackId);
        MailPackage mailPackage = mailPackageService.getById(packageId);
        if (findTrack.isPresent()) {
            Track track = findTrack.get();
            track.setMailPackage(mailPackage);
            return repository.save(track);
        } else throw new ResourceNotFoundException("Item is not found with id: " + trackId);
    }

    public Track addStartOffice(Long trackId, Long officeId) {
        PostOffice officeToAdd = postOfficeService.getById(officeId);
        Track track = this.getById(trackId);
        track.setFirstOffice(officeToAdd);
        return repository.save(track);
    }

    /**
     * Add list of PostOffices to distinct position in track.
     *
     * @param track      id of track for adding offices
     * @param from       PostOffice after which to add
     * @param officeList list to add
     * @return changed Track
     */
    public Track addOfficeFrom(Long track, PostOffice from, List<PostOffice> officeList) {
        Track trackToWork = this.getById(track);
        trackToWork.addPostOfficeAfter(from, officeList);
        return repository.save(trackToWork);
    }

    /**
     * Add PostOffice to distinct position in track.
     *
     * @param track  id of track for adding offices
     * @param from   PostOffice after which to add
     * @param office PostOffice to add
     * @return changed Track
     */
    public Track addOfficeFrom(Long track, PostOffice from, PostOffice office) {
        Track trackToWork = this.getById(track);
        trackToWork.addPostOfficeAfter(from, office);
        return repository.save(trackToWork);
    }


    /**
     * Add PostOffice to distinct position in track.
     *
     * @param track  id of track for adding offices
     * @param from   id of PostOffice after which to add
     * @param office id of PostOffice to add
     * @return changed Track
     */
    public Track addOfficeFrom(Long track, Long from, Long office) {
        Track trackToWork = this.getById(track);
        PostOffice officeToAdd = postOfficeService.getById(office);
        if (trackToWork.getPath().size() == 0) {
            trackToWork.setFirstOffice(officeToAdd);
        } else {
            PostOffice officeFrom = postOfficeService.getById(from);
            trackToWork.addPostOfficeAfter(officeFrom, officeToAdd);
        }
        return repository.save(trackToWork);
    }

    /**
     * Check is Track finished and package arrived
     *
     * @param track id of track for adding offices
     * @return arrive status
     */
    public boolean isArrived(Long track) {
        Track trackToWork = this.getById(track);
        return trackToWork.isArrived();
    }
}
