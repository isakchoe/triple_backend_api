package triple.pointApi.domain.place.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import triple.pointApi.domain.place.repository.PlaceRepository;
import triple.pointApi.global.entity.Place;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class PlaceService {

    private final PlaceRepository placeRepository;

    public Place getPlace(UUID placeId){
        Place place = placeRepository.findById(placeId).
                orElseThrow(() -> new NullPointerException());
        return place;
    }


}
