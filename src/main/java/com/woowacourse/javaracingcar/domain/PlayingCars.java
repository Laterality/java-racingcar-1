package com.woowacourse.javaracingcar.domain;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class PlayingCars {
    // update 등 이름으로 객체를 빠르게 검색하기 위한 맵
    private final Map<String, Car> cars;
    // 인자로 전달되는 Car 객체의 순서를 보존하기 위한 리스트
    private final List<Car> carList;

    public PlayingCars(final List<Car> cars) {
        if (cars == null) {
            throw new IllegalArgumentException("Argument is null");
        }
        this.carList = cars;
        this.cars = new HashMap<>();
        cars.forEach(c -> this.cars.put(c.getName(), c));
    }

    /**
     * @param positionDelta 움직일 거리
     */
    public List<CarDto> moveCars(Supplier<Integer> positionSupplier) {
        List<CarDto> movedCars = new ArrayList<>();
        carList.forEach(c -> movedCars.add(c.move(positionSupplier.get())));
        return movedCars;
    }

    public List<CarDto> retrieveAllCars() {
        return carList.stream()
            .map(c -> new CarDto(c.getName(), c.getPosition()))
            .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PlayingCars [ ");
        carList.forEach(sb::append);
        sb.append(" ]");
        return sb.toString();
    }
}
