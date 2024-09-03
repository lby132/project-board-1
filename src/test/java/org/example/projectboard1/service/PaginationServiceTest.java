package org.example.projectboard1.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@DisplayName("비즈니스 로직 - 페이지네이션")
// webEnvironment 의 기본 값은 MOCK인데 모킹한 실제 웹환경을 넣어주는것이다. 그런데 NONE으로 하면 웹환경을 전혀 넣지 않기 때문에 가볍게 테스트를 해볼수 있다.
// classes 속성은 @SpringBootTest 는 실행시 FastcampusProjectBoardApplication 하위 빈들을 모두 등록을 하게 되는데 이 부분에 빈들을 임의로 등록해 주거나 Void.class를 사용해서 아무것도 아니게 만들어버릴 수 있다.
// 그럼 @SpringBootTest 는 무게가 많이 줄어들게 된다.
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = PaginationService.class)
class PaginationServiceTest {

    private final PaginationService sut;

    public PaginationServiceTest(@Autowired PaginationService paginationService) {
        this.sut = paginationService;
    }

    @DisplayName("현재 페이지 번호와 총 페이지 수를 주면, 페이징 바 리스트를 만들어 준다.")
    @MethodSource // @ParameterizedTest 과 같이 사용하면 메서드 스타일로 파라미터를 여러개 받을 수 있다.
                //  givenCurrentPageNumberAndTotalPages_whenCalculating_thenReturnsPaginationBarNumbers static 메서드 처럼.
    @ParameterizedTest(name = "[{index} 현재 페이지: {0}, 총 페이지: {1} ==> {2}]")
    void givenCurrentPageNumberAndTotalPages_whenCalculating_thenReturnsPaginationBarNumbers(int currentPageNumber, int totalPages, List<Integer> expected) throws Exception {
        //given

        //when
        List<Integer> actual = sut.getPaginationBarNumbers(currentPageNumber, totalPages);

        //then
        assertThat(actual).isEqualTo(expected);
    }

    static Stream<Arguments> givenCurrentPageNumberAndTotalPages_whenCalculating_thenReturnsPaginationBarNumbers() {
        return Stream.of(
                arguments(0, 13, List.of(0, 1, 2, 3, 4)),
                arguments(1, 13, List.of(0, 1, 2, 3, 4)),
                arguments(2, 13, List.of(0, 1, 2, 3, 4)),
                arguments(3, 13, List.of(1, 2, 3, 4, 5)),
                arguments(4, 13, List.of(2, 3, 4, 5, 6)),
                arguments(5, 13, List.of(3, 4, 5, 6, 7)),
                arguments(6, 13, List.of(4, 5, 6, 7, 8)),
                arguments(10, 13, List.of(8, 9, 10, 11, 12)),
                arguments(11, 13, List.of(9, 10, 11, 12)),
                arguments(12, 13, List.of(10, 11, 12))
        );
    }

    @DisplayName("현재 설정되어 있는 페이지네이션 바의 길이를 알려준다.")
    @Test
    void givenNothing_whenCalling_thenReturnsCurrentBarLength() throws Exception {
        //given

        //when
        int barLength = sut.currentBarLength();

        //then
        assertThat(barLength).isEqualTo(5);
    }

}