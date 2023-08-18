package com.testcase.skyeng.controllers.modelControllers;

import com.testcase.skyeng.controllers.CommonController;
import com.testcase.skyeng.models.PostOffice;
import com.testcase.skyeng.models.Track;
import com.testcase.skyeng.services.modelServices.PostOfficeService;
import com.testcase.skyeng.services.modelServices.TrackService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tracks")
public class TrackController extends CommonController<Track, TrackService> {
    protected TrackController(TrackService service, PostOfficeService postOfficeService) {
        super(service);
    }

    @Override
    public Track newItem(@RequestBody Track newItem) {

        return super.newItem(newItem);
    }

    @PostMapping("/path/{id}")
    public Track nextStep(@PathVariable Long id) {
        Track findTrack = service.getByIdOrNull(id);
        findTrack.moveToNextStep();
        return service.saveItem(findTrack);
    }

    @GetMapping("/path/{id}")
    public PostOffice checkCurrentOffice(@PathVariable Long id) {
        Track findTrack = service.getByIdOrNull(id);
        return findTrack.getCurrentOffice();
    }

    @PutMapping("/path/{id}")
    public Track addOfficeToTrack(@PathVariable Long id,
                                  @RequestParam(name = "officeFromId") Long officeFromId,
                                  @RequestParam(name = "officeId") Long officeId) {
        return service.addOfficeFrom(id, officeFromId, officeId);
    }
}
