package BaekGwa.IndexTest.controller;

import BaekGwa.IndexTest.service.TestService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
//@RequestMapping
public class TestController {

    private final TestService testService;

    @GetMapping("/name")
    private String searchName(
            @RequestParam(name = "name") String name
    ) {
        testService.searchByName(name);
        return "ok";
    }

    @GetMapping("/teamid/backnumber")
    private String searchTeamIdAndBackNumber(
            @RequestParam(name = "teamId") Long teamId,
            @RequestParam(name = "backNumber") Long backNumber
    ) {
        testService.searchTeamIdAndBackNumber(teamId, backNumber);
        return "ok";
    }

    @GetMapping("/covering")
    private String coveringTest(
            @RequestParam(name = "teamId") Long teamId,
            @RequestParam(name = "backNumber") Long backNumber
    ) {
        testService.coveringIndexTest(teamId, backNumber);
        return "ok";
    }
}
