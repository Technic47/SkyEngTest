package com.testcase.skyeng.services.modelServices;

import com.testcase.skyeng.models.PostOffice;
import com.testcase.skyeng.models.Track;
import com.testcase.skyeng.repositories.TrackRepository;
import com.testcase.skyeng.services.CommonService;
import org.springframework.stereotype.Service;

@Service
public class TrackService extends CommonService<Track, TrackRepository> {
    private final PostOfficeService postOfficeService;
    public TrackService(TrackRepository repository, PostOfficeService postOfficeService) {
        super(repository);
        this.postOfficeService = postOfficeService;
    }


    public Track addOfficeFrom(Long track, Long from, Long office){
        Track trackToWork = this.getByIdOrNull(track);
        PostOffice officeFrom = postOfficeService.getByIdOrNull(from);
        PostOffice officeToAdd = postOfficeService.getByIdOrNull(office);
        trackToWork.addPostOfficeAfter(officeFrom, officeToAdd);
        return repository.save(trackToWork);
    }

    public boolean isArrived(Long track){
        Track trackToWork = this.getByIdOrNull(track);
        return trackToWork.isArrived();
    }
}
