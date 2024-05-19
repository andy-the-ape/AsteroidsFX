package dk.sdu.mmmi.cbse.pointsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RequestMapping("/api")
@RestController
@CrossOrigin
public class PointSystem {
    private Long scoreTally = 0L;
    public static void main(String[] args) {
        SpringApplication.run(PointSystem.class, args);
    }

    @GetMapping("/score")
    public Long getScoreTally() {
        return scoreTally;
    }

    @PutMapping("/score/update/{points}")
    public Long updateScoreTally(@PathVariable(value = "points") Long points) {
        scoreTally += points;
        return scoreTally;
    }

    @PutMapping("/score/reset")
    public Long resetScoreTally() {
        scoreTally = 0L;
        return scoreTally;
    }
}
