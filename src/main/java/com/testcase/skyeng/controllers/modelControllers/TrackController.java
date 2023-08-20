package com.testcase.skyeng.controllers.modelControllers;

import com.testcase.skyeng.controllers.CommonController;
import com.testcase.skyeng.exceptions.ResourceNotFoundException;
import com.testcase.skyeng.models.PostOffice;
import com.testcase.skyeng.models.Track;
import com.testcase.skyeng.services.modelServices.MailPackageService;
import com.testcase.skyeng.services.modelServices.PostOfficeService;
import com.testcase.skyeng.services.modelServices.TrackService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/tracks")
public class TrackController extends CommonController<Track, TrackService> {
    private final MailPackageService mailPackageService;
    private final PostOfficeService postOfficeService;

    protected TrackController(TrackService service, MailPackageService mailPackageService, PostOfficeService postOfficeService) {
        super(service);
        this.mailPackageService = mailPackageService;
        this.postOfficeService = postOfficeService;
    }

//    @Override
//    public Track newItem(@RequestBody Track newItem) {
//        return super.newItem(newItem);
//    }

    @PostMapping("/{trackId}/addPackage/{packageId}")
    public Track addPackage(@PathVariable Long trackId,
                            @PathVariable Long packageId) {
        try {
            return service.addPackage(trackId, packageId);
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @PostMapping("/{trackId}/addStart/{officeId}")
    public Track addStart(@PathVariable Long trackId,
                          @PathVariable Long officeId){
        try {
            return service.addStartOffice(trackId, officeId);
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @PostMapping("/path/{id}")
    public Track nextStep(@PathVariable Long id) {
        Track findTrack = service.getById(id);
        findTrack.moveToNextStep();
        return service.saveItem(findTrack);
    }

    @GetMapping("/path/{id}")
    public PostOffice checkCurrentOffice(@PathVariable Long id) {
        Track findTrack = service.getById(id);
        return findTrack.getCurrentOffice();
    }

    @PutMapping("/path/{id}")
    public Track addOfficeToTrack(@PathVariable Long id,
                                  @RequestParam Long officeFrom,
                                  @RequestParam Long officeToAdd) {
        return service.addOfficeFrom(id, officeFrom, officeToAdd);
    }
}
