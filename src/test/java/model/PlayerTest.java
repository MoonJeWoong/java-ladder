package model;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * 사다리 게임 참여자 정보를 가지는 클래스.
 * 원시타입 데이터의 getter는 테스트하지 않는다.
 */
public class PlayerTest {
    @Test
    @DisplayName("Player 객체 생성 성공 테스트")
    void createPlayerTest() {
        Assertions.assertThatNoException().isThrownBy(() -> new Player(new Name("pobi"), new Position(0)));
    }

    @Test
    @DisplayName("참여자의 이름 정보가 일치하는지 확인할 수 있는 기능 테스트")
    void isPlayerNameTest() {
        //given
        Player player = new Player(new Name("pobi"), new Position(0));

        //when
        boolean result = player.isPlayerName(new Name("pobi"));

        //then
        assertThat(result).isEqualTo(true);
    }

    @Test
    @DisplayName("사다리 게임의 개인 결과를 저장하는 기능 테스트")
    void savePlayerResultTest() {
        //given
        Player player = new Player(new Name("pobi"), new Position(0));
        Result result = new Result("5000");

        //when
        player.saveResult(result);

        //then
        assertThat(player.getResult()).isEqualTo("5000");
    }

    @ParameterizedTest
    @CsvSource(value = {"0:0:true", "0:1:false"}, delimiter = ':')
    @DisplayName("참여자의 현재 위치와 주어진 위치 값의 동일 여부 확인 기능 테스트")
    void savePlayerPositionTest(int playerPosition, int inputNumber, boolean expected) {
        //given
        Player player = new Player(new Name("hiiro"), new Position(playerPosition));

        //when
        boolean result = player.isPlayerPosition(new Position(inputNumber));

        //then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("참여자 간 위치를 변경하는 기능 테스트")
    void changePlayerPositionTest() {
        //given
        Position zeroPosition = new Position(0);
        Position firstPosition = new Position(1);
        Player hiiro = new Player(new Name("hiiro"), zeroPosition);
        Player ocean = new Player(new Name("ocean"), firstPosition);

        //when
        hiiro.changePositionWith(ocean);

        //then
        assertThat(hiiro.isPlayerPosition(firstPosition)).isTrue();
        assertThat(ocean.isPlayerPosition(zeroPosition)).isTrue();
    }
}
