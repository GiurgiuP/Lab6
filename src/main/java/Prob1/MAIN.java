package Prob1;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import static java.lang.System.exit;

public class MAIN
{
    public static void scriere(List<Angajat> lista)
    {
        try
        {
            ObjectMapper mapper=new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            File file=new File("src/main/resources/angajati.json");
            mapper.writeValue(file,lista);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static List<Angajat> citire()
    {
        try
        {
            File file=new File("src/main/resources/angajati.json");
            ObjectMapper mapper=new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            List<Angajat> persoane = mapper.readValue(file, new TypeReference<List<Angajat>>(){});
            return persoane;
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args)
    {
        List<Angajat> angajati=citire();
        int opt;
        Scanner sc=new Scanner(System.in);
        do
        {
            System.out.println("\nMeniu:");
            System.out.println("1. Afisarea listei de angajati");
            System.out.println("2. Afisarea angajatilor cu salariul peste 2500 RON");
            System.out.println("3. Crearea unei liste cu angajatii din aprilie, anul trecut, cu functie de conducere");
            System.out.println("4. Afisarea angajatilor fara functie de conducere, ordonati descrescator dupa salariu");
            System.out.println("5. Afisarea numelor angajatilor cu majuscule");
            System.out.println("6. Afisarea salariilor mai mici de 3000 RON");
            System.out.println("7. Afisarea datelor primului angajat");
            System.out.println("8. Afisarea statisticilor salariilor angajatilor");
            System.out.println("9. Verificare existenta angajat „Ion”");
            System.out.println("10. Afisarea numarului de angajati ,angajati in vara anului trecut");
            System.out.println("0. Iesire");
            System.out.print("Alegeți o opțiune: ");
            opt= sc.nextInt();
            switch (opt)
            {
                case 1:AfisareLista(angajati);break;
                case 2:Afisare2500(angajati);break;
                case 3:ListaAprilie(angajati);break;
                case 4:AfisareFaraFunc(angajati);break;
                case 5:AfisareMajuscule(angajati);break;
                case 6:Afisare3000(angajati);break;
                case 7:AfisarePrim(angajati);break;
                case 8:AfisareStatis(angajati);break;
                case 9:Verificare(angajati);break;
                case 10:VaraPrec(angajati);break;
                case 0:exit(0);break;


            }


        }while(opt!=0);

    }

 public static void AfisareLista(List<Angajat> angajati)
 {
    angajati.forEach(System.out::println);
 }
 public static void Afisare2500(List<Angajat> angajati)
 {
     angajati.stream().filter(angajat -> angajat.getSalariul() > 2500).forEach(System.out::println);
 }
 public static void ListaAprilie(List<Angajat> angajati)
 {
     int anCurent= LocalDate.now().getYear();
     int anTrecut=anCurent-1;


     List<Angajat> angajatiAprilie = angajati.stream()
             .filter(angajat -> angajat.getData_angajarii().getYear() == anTrecut && angajat.getData_angajarii().getMonthValue() == 4)
             .filter(angajat -> angajat.getPostul().toLowerCase().contains("sef") || angajat.getPostul().toLowerCase().contains("director"))
             .collect(Collectors.toList());

     angajatiAprilie.forEach(System.out::println);

 }
 public static void AfisareFaraFunc(List<Angajat> angajati)
 {
     angajati.stream()
             .filter(angajat -> !(angajat.getPostul().toLowerCase().contains("director") || angajat.getPostul().toLowerCase().contains("sef")))
             .sorted((angajat1, angajat2) -> Double.compare(angajat2.getSalariul(), angajat1.getSalariul()))
             .forEach(System.out::println);
 }
public static void AfisareMajuscule(List<Angajat> angajati)
{
    List<String> numeMajuscule = angajati.stream()
            .map(angajat -> angajat.getNumele().toUpperCase())
            .collect(Collectors.toList());

    numeMajuscule.forEach(System.out::println);
}
public static void Afisare3000(List<Angajat> angajati)
{
    angajati.stream()
            .filter(angajat -> angajat.getSalariul() < 3000)
            .map(Angajat::getSalariul)
            .forEach(System.out::println);
}
public static void AfisarePrim(List<Angajat> angajati)
{
    Optional<Angajat> primulAngajat = angajati.stream().min(Comparator.comparing(Angajat::getData_angajarii));
    if (primulAngajat.isPresent())
    {
        System.out.println(primulAngajat.get());
    } else
    {
        System.out.println("Nu există angajați în firmă.");
    }
}

public static void AfisareStatis(List<Angajat>angajati)
{
    DoubleSummaryStatistics statisticiSalarii = angajati.stream().collect(Collectors.summarizingDouble(Angajat::getSalariul));
    System.out.println("Salariul mediu: " + statisticiSalarii.getAverage());
    System.out.println("Salariul minim: " + statisticiSalarii.getMin());
    System.out.println("Salariul maxim: " + statisticiSalarii.getMax());
}

public static void Verificare(List<Angajat> angajati)
{
    Optional<Angajat> angajatIon = angajati.stream()
            .filter(angajat -> angajat.getNumele().toLowerCase().contains("ion"))
            .findAny();


    angajatIon.ifPresentOrElse(angajat -> System.out.println("Firma are cel putin un Ion angajat"), () -> System.out.println("Firma nu are nici un Ion angajat"));
}

public static void VaraPrec(List<Angajat> angajati)
{
    int anCurent = LocalDate.now().getYear();
    int anTrecut = anCurent - 1;

    long numarAngajatiVara = angajati.stream()
            .filter(angajat -> angajat.getData_angajarii().getYear() == anTrecut)
            .filter(angajat ->
            {
                int luna = angajat.getData_angajarii().getMonthValue();
                return luna >= 6 && luna <= 8;
            })
            .count();

    System.out.println("Numarul de angajati care s-au angajat vara anului trecut: " + numarAngajatiVara);
}

}
