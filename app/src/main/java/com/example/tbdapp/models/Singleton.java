package com.example.tbdapp.models;

import android.widget.ImageView;

import com.example.tbdapp.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Singleton {
    public ArrayList<Advisor> advisors;
    public User user;
    public HashMap<String, ArrayList<Message>> chatHistory;

    private String placeholderText = "This text is placeholder text.";
    private String image_3 = "image_3";
    private String image_1 = "image_1";
    private String image_2 = "image_2";
    private String image_4 = "image_4";
    private String image_5 = "image_5";
    private String image_6 = "image_6";
    private String image_7 = "image_7";
    private String image = "image";



    private static Singleton instance;

    private Singleton() {
        advisors = new ArrayList<>();
        createAdvisorProfiles();

        user = new User("Jane Foster", "profile_picture", "Jane", "1996-09-04", "janefoster@tbd.com", "Ontario", "Permanent Resident", "Unemployed", "$10,000", "Tenant", "None", "A financial advisor");

        chatHistory = new HashMap<>();
        populateChatHistories();
        populateMessageList();
    }

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    public static Message getLastMessage(ArrayList<Message> messageList) {
        if(messageList.size() == 0) {
            return null;
        }
        return messageList.get(0);
    }

    private void createAdvisorProfiles() {
        advisors.add(new Advisor("1", "Brittany Williams", "Career Advisor","Proactive, organized and friendly Career Advisor that is ready for anything. I possess 5 years of experience in the field which include training in CVs,  cover letters and other career advisor areas.",
                "- Support back to employment\n" +
                "- Diary Management\n" +
                "- Action plan creation\n" +
                "- Job serach sesions\n" +
                "- Communication\n" +
                "- Working with culturally diverse populations\n" +
                "- Maintains positive atmosphere\n" +
                "- Key relationship management\n" +
                "- Job search strategies\n" +
                "- Motivating Jobseekers\n", image_3));
        advisors.add(new Advisor("2", "Thomas Edwards", "Career Advisor", "Talented Career Advisor with expertise in helping unemployed individuals and college graduates find gainful employment. Organised, proactive, and detail-orientated professional with strong communication and interpersonal abilities.\n", "- Resource advocacy\n" +
                "- Career planning\n" +
                "- Database management\n" +
                "- Networking\n" +
                "- Coaching\n" +
                "- Job search strategies\n" +
                "- Social media savvy\n" +
                "- Key relationship management\n" +
                "- Training opportunities\n" +
                "- Motivating jobseekers", image_7));
        advisors.add(new Advisor("3", "Daniel Wilson", "Financial Advisor", "Daniel is a competent Financial Advisor with experience in countless different situations. Daniel has over 8 years of successful business development.", "- Investment Strategies\n" +
                "- Relationship Building\n" +
                "- Networking\n" +
                "- Asset Management\n" +
                "- Cross-selling\n" +
                "- Social media savvy\n" +
                "- Stratagize Business Planning\n" +
                "- Client/Investor Relations\n" +
                "- New Business Development", image_4));
        advisors.add(new Advisor("4", "Jaimie Miller", "Financial Advisor", "Jaimie focuses in portfolio diversity and secure investments. He excels at identifying and evaluating potential investment oppourtunities worldwide.", "- Business level Japanese and Mandarin\n" +
                "- Analytical skills\n" +
                "- Multi-Tasking\n" +
                "- Problem Solving\n" +
                "- Technology Integration\n" +
                "- Networking\n" +
                "- Individual Client Consultation\n" +
                "- Policy Reviews\n" +
                "- Prospecting\n" +
                "- Compliance", image_5));
        advisors.add(new Advisor("5", "Raiqah Johal", "Career Advisor", "Raiqah works as a career coach in helping people understand their career goals. She has an ability to implement new initiatives regardless of the task or job you’re searching for.", "- Social media savvy\n" +
                "- Key relationship management\n" +
                "- Training opportunities\n" +
                "- Motivating job-seekers\n" +
                "- Social media savvy\n" +
                "- Resource advocacy\n" +
                "- Career planning\n" +
                "- Database management\n" +
                "- Networking\n" +
                "- Coaching\n" +
                "- Job search strategies", image_6));
        advisors.add(new Advisor("6", "Bob Northchuck", "Financial Advisor", "Profesional Financial Advisor who is always ready to help with more than 20 years succsefully implementing wealth management, risk management and investment and financial strategies. ", "- Financial Management\n" +
                "- Management\n" +
                "- Microsoft Office\n" +
                "- Autocad\n" +
                "- Engineering Management\n" +
                "- Financial Analysis\n" +
                "- Portfolio Analysis\n" +
                "- Customer Service\n" +
                "- Team Building\n" +
                "- Collaboration\n" +
                "- Calm Under Pressure\n" +
                "- Personable assistant", image_2));
        advisors.add(new Advisor("7", "Alice Cooper", "Career Advisor", "Alice is a career advisor that can guarantee your entry into the career of your dreams. She is an expert at networking events and getting that first foot in the door.\n", "- Communication Skills\n" +
                "- Networking\n" +
                "- Program Implementation\n" +
                "- Career Presentations\n" +
                "- Financial Aid Appeals\n" +
                "- Leadership\n" +
                "- Mentorship\n" +
                "- Calm Under Pressure", image));
        /*
        advisors.add(new Advisor("8", "Alexei Dubrivonich", "Financial", placeholderText, placeholderText, placeholderImage));
        advisors.add(new Advisor("9", "Aetherbald the Great", "Financial", placeholderText, placeholderText, placeholderImage));
        advisors.add(new Advisor("10", "Pyńęł Rziąćic", "Financial", placeholderText, placeholderText, placeholderImage));
        advisors.add(new Advisor("11", "Cornelius II of Rome", "Financial", placeholderText, placeholderText, placeholderImage));
        advisors.add(new Advisor("12", "Rünerig Pfochmännördt", "Financial", placeholderText, placeholderText, placeholderImage));
        advisors.add(new Advisor("13", "Grzegorz Brzęczyszczykiewicz", "Financial", placeholderText, placeholderText, placeholderImage)); */
    }

    private void populateChatHistories() {
        for(int i=0;i<advisors.size();i++) {
            chatHistory.put(advisors.get(i).id, new ArrayList<Message>());
        }
    }

    private void populateMessageList() {
        for(int i=0;i<advisors.size();i++) {
            for(int j=1;j<11;j++) {
                if(j%2==0) {
                    chatHistory.get(String.valueOf(i+1)).add(new Message("0","test", user, new Date()));
                } else {
                    chatHistory.get(String.valueOf(i+1)).add(new Message("1","test", advisors.get(i), new Date()));
                }
            }
        }
    }
}
