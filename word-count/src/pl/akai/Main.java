package pl.akai;

import java.util.*;

public class Main {

    private static String[] sentences = {
            "Taki mamy klimat",
            "Wszędzie dobrze ale w domu najlepiej",
            "Wyskoczył jak Filip z konopii",
            "Gdzie kucharek sześć tam nie ma co jeść",
            "Nie ma to jak w domu",
            "Konduktorze łaskawy zabierz nas do Warszawy",
            "Jeżeli nie zjesz obiadu to nie dostaniesz deseru",
            "Bez pracy nie ma kołaczy",
            "Kto sieje wiatr ten zbiera burzę",
            "Być szybkim jak wiatr",
            "Kopać pod kimś dołki",
            "Gdzie raki zimują",
            "Gdzie pieprz rośnie",
            "Swoją drogą to gdzie rośnie pieprz?",
            "Mam nadzieję, że poradzisz sobie z tym zadaniem bez problemu",
            "Nie powinno sprawić żadnego problemu, bo Google jest dozwolony"
    };

    public static void main(String[] args) {
        /* TODO Twoim zadaniem jest wypisanie na konsoli trzech najczęściej występujących słów
                w tablicy 'sentences' wraz z ilością ich wystąpień..

                Przykładowy wynik:
                1. "mam" - 12
                2. "tak" - 5
                3. "z" - 2
        */

        String a=String.join(" ", sentences);
        System.out.println(a);
        List<String> words = Arrays.stream(String.join(" ", sentences).split(" ")).map(String::toLowerCase).toList();

        Map<String,Integer> word_map=new HashMap<>();

        for (var word : words){
            if(word_map.containsKey(word))
                word_map.put(word,word_map.get(word)+1);
            else word_map.put(word,1);
        }

        var top_words=new LinkedList<>(word_map.entrySet());
        top_words.sort(Collections.reverseOrder(Map.Entry.comparingByValue()));

        for(int i=0;i<=2;i++)
            System.out.println((i+1) + ". \"" + top_words.get(i).getKey() +"\" - "+ top_words.get(i).getValue());

    }

}