package com.testcase.skyeng.services.modelServices;

import com.testcase.skyeng.models.PostOffice;
import com.testcase.skyeng.models.Track;
import com.testcase.skyeng.repositories.TrackRepository;
import com.testcase.skyeng.services.CommonService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrackService extends CommonService<Track, TrackRepository> {
    private final PostOfficeService postOfficeService;

    public TrackService(TrackRepository repository, PostOfficeService postOfficeService) {
        super(repository);
        this.postOfficeService = postOfficeService;
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
        Track trackToWork = this.getByIdOrNull(track);
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
        Track trackToWork = this.getByIdOrNull(track);
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
        Track trackToWork = this.getByIdOrNull(track);
        PostOffice officeFrom = postOfficeService.getByIdOrNull(from);
        PostOffice officeToAdd = postOfficeService.getByIdOrNull(office);
        trackToWork.addPostOfficeAfter(officeFrom, officeToAdd);
        return repository.save(trackToWork);
    }

    /**
     * Check is Track finished and package arrived
     *
     * @param track id of track for adding offices
     * @return arrive status
     */
    public boolean isArrived(Long track) {
        Track trackToWork = this.getByIdOrNull(track);
        return trackToWork.isArrived();
    }
}
