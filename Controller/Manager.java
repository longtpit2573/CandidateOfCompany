package CandidateOfCompany.Controller;

import java.util.ArrayList;

import CandidateOfCompany.Common.Validate;
import CandidateOfCompany.Model.Candidate;
import CandidateOfCompany.Model.Experience;
import CandidateOfCompany.Model.Fresher;
import CandidateOfCompany.Model.Intern;
import CandidateOfCompany.View.Menu;

public class Manager extends Menu<String> {

    static Validate check = new Validate();

    static String[] mc = { "Experience", "Fresher", "Internship", "Searching", "Exit" };
    static ArrayList<Candidate> candidates = new ArrayList<>();


    public Manager() {
        super("======Candidate management system======", mc);
    }

    @Override
    public void execute(int n) {

        switch (n) {
            case 1:
                add(candidates, 0);
                break;
            case 2:
                add(candidates, 1);
                break;
            case 3:
                add(candidates, 2);
                break;
            case 4:
                search(candidates);
                break;
            case 5:
                System.exit(0);
        }
    }


    public void add(ArrayList<Candidate> candidates, int type) {

        while (true) {
            System.out.println("Enter id: ");
            String id = check.getString();
            if (check.isCandidateId(candidates, id) == -1) {
                System.out.println("Enter your first name: ");
                String fName = check.getString();
                System.out.println("Enter your last name: ");
                String lName = check.getString();
                int birthDate = check.getInt("Enter your birthDate: ", 1900, 2023);
                System.out.println("Enter your address: ");
                String address = check.getString();
                System.out.println("Enter phone number: ");
                String phone = check.getphoneNumber(candidates);
                System.out.println("Enter email: ");
                String email = check.getEmail(candidates);

                Candidate cd = new Candidate(id, fName, lName, birthDate, address, phone, email, type);

                switch (type) {
                    case 0:
                        createExperience(candidates, cd);
                        break;
                    case 1:
                        createFresher(candidates, cd);
                        break;
                    case 2:
                        createIntern(candidates, cd);
                        break;
                }

            } else {
                System.out.println("Id existed!");
            }
            System.out.println("Do you want to continue? (Y/N)");
            if (!check.chooseYN()) {
                return;
            }
        }

    }

    public void createExperience(ArrayList<Candidate> candidates, Candidate cd) {
        int yearExperience = check.getInt("Enter year of experience", 0, 100);
        System.out.println("Enter professional skill: ");
        String proSkill = check.getString();

        candidates.add(new Experience(yearExperience, proSkill,
                cd.getId(), cd.getFirstName(), cd.getLastName(),
                cd.getBirthDate(), cd.getAddress(),
                cd.getPhone(), cd.getEmail(), cd.getType()));
        System.out.println("Experience created!");
    }

    public void createFresher(ArrayList<Candidate> candidates, Candidate cd) {
        System.out.println("Enter graduation date: ");
        String graDate = check.getString();
        System.out.println("Enter graduation rank: ");
        String graRank = check.getRankOfGraduation();
        System.out.println("Enter university: ");
        String education = check.getString();

        candidates.add(new Fresher(graDate, graRank, education,
                cd.getId(), cd.getFirstName(), cd.getLastName(),
                cd.getBirthDate(), cd.getAddress(),
                cd.getPhone(), cd.getEmail(), cd.getType()));
        System.out.println("Fresher created!");
    }

    public void createIntern(ArrayList<Candidate> candidates, Candidate cd) {
        System.out.println("Enter major: ");
        String major = check.getString();
        int semester = check.getInt("Enter semester: ", 1, 9);
        System.out.println("Enter university: ");
        String university = check.getString();

        candidates.add(new Intern(major, semester, university,
                cd.getId(), cd.getFirstName(), cd.getLastName(),
                cd.getBirthDate(), cd.getAddress(), cd.getPhone(),
                cd.getEmail(), cd.getType()));
        System.out.println("Intern created!");
    }

    public void search(ArrayList<Candidate> candidates) {
        int count = 0;

        displayListCandidate(candidates);
        System.out.println("\nEnter name to search: ");
        String nameSearch = check.getString().toUpperCase();
        int cdType = check.getInt("Enter candidate type: ", 0, 2);

        for (Candidate cd : candidates) {
            if (cdType == cd.getType() && (cd.getFirstName().toUpperCase().contains(nameSearch) || cd.getLastName().toUpperCase().contains(nameSearch))) {
                count++;
                if (count == 1) {
                    System.out.println("The candidate found!");
                }
                System.out.println(cd.toString());
            }
        }
        if (count == 0) {
            System.out.println("Candidate not found!");
        }

    }

    public void displayListCandidate(ArrayList<Candidate> candidates) {
        System.out.println("=========Experience========");

        for (Candidate cd : candidates) {
            if (cd instanceof Experience) {
                System.out.println(cd.getFirstName() + " " + cd.getLastName());
            }
        }

        System.out.println("\n==========Fresher=========");

        for (Candidate cd : candidates) {
            if (cd instanceof Fresher) {
                System.out.println(cd.getFirstName() + " " + cd.getLastName());
            }
        }

        System.out.println("\n==========Intern==========");

        for (Candidate cd : candidates) {
            if (cd instanceof Intern) {
                System.out.println(cd.getFirstName() + " " + cd.getLastName());
            }
        }
    }


}
