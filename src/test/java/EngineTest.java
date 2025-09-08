import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EngineTest {
    @Test
    void moveEnemyRandomTest(){
        assertTrue(Engine.moveEnemyRandom());
    }
}