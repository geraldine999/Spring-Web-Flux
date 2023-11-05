package com.example.Spring.Web.Flux;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

public class FluxAndMonoGeneratorService {

    public static Flux<String> namesFlux(){
        return Flux.fromIterable(List.of("Jen", "Ken", "Stacy"))
                .log(); //db or a remote service call

        //on the logs we can see that firstly the subscriber subscribes to the publisher (onSubscribe())
        //then the publisher sends each element as events (onNext(element)), and the subscriber
        //reacts to those events, then the publisher announces when it has finished
        //sending the elements (onComplete())
    }

    public static Mono<String> nameMono(){
        return Mono.just("Alex");
    }

    public static Flux<String> namesFluxToUpperCase(){
        return Flux.fromIterable(List.of("Jen", "Ken", "Stacy"))
                .map(String::toUpperCase);
    }
    public static Flux<String> namesFluxFilterByStringLength(int minStringLength){
        return Flux.fromIterable(List.of("Jen", "Ken", "Stacy"))
                .filter(s-> s.length() > minStringLength);
    }

    public static Flux<String> animalNamesFlatMap(){
        return Flux.fromIterable(List.of("Dog", "Cat", "Wolf"))
                .flatMap(s->splitStringWithDelay(s))
                .log();
    }

    public static Flux<String> transformAnimals(){
        //behaviour parameterization
        //functional interfaces-> you can extract a functionality and assign it to a variable
        Function<Flux<String>, Flux<String>> filterMap= name -> name.map(String::toUpperCase);

        return Flux.fromIterable(List.of("Deer", "Giraffe", "Crocodile"))
                .transform(filterMap);

        //transform()-> useful if you have a common functionality that is being used across your project
    }

    //map() -> used for one to one transformations- sync
    //flatMap()-> used for one to N transformations- async, order can be altered
    //use concatMap() when ordering matters in async functions

    public static Flux<String> splitString(String name){
        var charArray = name.split("");
        return Flux.fromArray(charArray);
    }

    public static Flux<String> splitStringWithDelay(String name){
        var charArray = name.split("");
        var delay =  new Random().nextInt(1000);
        return Flux.fromArray(charArray)
                .delayElements(Duration.ofMillis(delay));
    }

    public static void main(String[] args) {
        namesFlux().subscribe(
                System.out::println
        ); //elements will be sent in the form of a stream, one by one

        nameMono().subscribe(System.out::println);

        namesFluxFilterByStringLength(3).subscribe(System.out::println);

        transformAnimals().subscribe(s-> System.out.println(s));

    }
}
