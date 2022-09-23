package com.tts.validation;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tts.validation.Rental.RentalBuilder;

@RestController
public class RentalController {
	@PostMapping("/rentals")	
	public ResponseEntity<Void> createRental(@RequestBody @Valid Rental rental, BindingResult result) {
		//if(repository.findByEmailAddress(rental.getEmailAddress()) != null) {
		//	result.rejectValue("emailAddress", "error.email", "Email address is already taken");
		//}
		
		if(result.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		//repository.save(rental);
		return new ResponseEntity<>(HttpStatus.CREATED);		
	}
	
	/*
	@GetMapping("/dealerships/{dealershipId}/cars/")
	public ResponseEntity<List<Car>> getCars(@PathVariable(value="dealershipId") Long dealershipId,
	                                         @RequestParam(value="color", required=false) String color
	                                         @RequestParam(value="sort", required=false) String sort) {
	    Dealer dealer = dealerRepository.findById(dealershipId);
	    if (dealer == null){
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	    List<Car> cars;
	    if (color != null && sort != null && sort.equalsIgnoreCase("msrp")){
	        cars = carRepository.findAllByDealerAndColorOrderByMsrpDesc(dealer, color);
	    }
	    else if (color != null){
	        cars = carRepository.findAllByDealerAndColor(dealer, color);
	    }
	    else if (sort != null && sort.equalsIgnoreCase("msrp")){
	        cars = carRepository.findAllByDealerOrderByMsrpDesc(dealer);
	    }
	    else {
	        cars = carRepository.findAllByDealer(dealer);
	    }
	    return new ResponseEntity<>(cars, HttpStatus.OK);
	}
	*/
}
