package com.hotelbookingapp;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class HotelListActivity extends AppCompatActivity {

    private ListView listView;
    private HotelAdapter adapter;
    private List<Hotel> hotelList;
    private List<Hotel> filteredList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Null check before setting content
        try {
            setContentView(R.layout.activity_hotel_list);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error loading layout", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // Enable back button with null check
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Available Hotels");
        }

        listView = findViewById(R.id.hotelListView);

        if (listView == null) {
            Toast.makeText(this, "Error: ListView not found", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // Initialize hotel data
        initializeHotels();

        // Create custom adapter
        adapter = new HotelAdapter(filteredList);
        listView.setAdapter(adapter);
    }

    private void initializeHotels() {
        hotelList = new ArrayList<>();

        // Using placeholder image - replace R.drawable.ic_hotel_placeholder with your actual drawables
        // or use android.R.drawable.ic_menu_gallery as fallback
        int defaultImage = android.R.drawable.ic_menu_gallery;

        hotelList.add(new Hotel("Kigali Serena Hotel", 312500, 4.8f, "Luxury", "Kigali",
                "Five-star luxury hotel with stunning city views, world-class dining, spa facilities, and impeccable service. Perfect for business and leisure travelers.",
                getDrawableResource("hotel_serena", defaultImage)));

        hotelList.add(new Hotel("Radisson Blu Hotel & Convention Centre Kigali", 337500, 4.7f, "Luxury", "Kigali",
                "Modern luxury hotel featuring state-of-the-art convention facilities, rooftop pool, multiple restaurants, and panoramic views of Kigali's hills.",
                getDrawableResource("hotel_radisson", defaultImage)));

        hotelList.add(new Hotel("Kigali Marriott Hotel", 325000, 4.8f, "Luxury", "Kigali",
                "Sophisticated accommodation in the heart of Kigali with elegant rooms, fitness center, rooftop terrace, and exceptional conference facilities.",
                getDrawableResource("hotel_marriott", defaultImage)));

        hotelList.add(new Hotel("Park Inn by Radisson Kigali", 225000, 4.5f, "Business", "Kigali",
                "Contemporary business hotel offering comfortable rooms, meeting spaces, restaurant & bar, and convenient access to the business district.",
                getDrawableResource("hotel_parkinn", defaultImage)));

        hotelList.add(new Hotel("2000 Hotel Downtown Kigali", 112500, 4.3f, "Budget", "Kigali",
                "Affordable downtown accommodation with clean rooms, friendly service, restaurant, and easy access to local markets and attractions.",
                getDrawableResource("hotel_2000", defaultImage)));

        hotelList.add(new Hotel("The Retreat by Heaven", 400000, 4.9f, "Boutique", "Kigali",
                "Exclusive boutique hotel nestled in lush gardens, offering intimate luxury, farm-to-table dining, spa treatments, and personalized service.",
                getDrawableResource("hotel_retreat", defaultImage)));

        hotelList.add(new Hotel("One&Only Gorilla's Nest", 1187500, 4.9f, "Safari Luxury", "Volcanoes National Park",
                "Ultra-luxury safari lodge at the foothills of the Virunga volcanoes. Exclusive gorilla trekking experiences, infinity pool, and breathtaking mountain views.",
                getDrawableResource("hotel_oneonly", defaultImage)));

        hotelList.add(new Hotel("Singita Kwitonda Lodge", 1500000, 5.0f, "Safari Luxury", "Volcanoes National Park",
                "Rwanda's most luxurious lodge offering unparalleled gorilla experiences, award-winning architecture, private suites, farm-to-table cuisine, and conservation focus.",
                getDrawableResource("hotel_singita", defaultImage)));

        hotelList.add(new Hotel("Lake Kivu Serena Hotel", 262500, 4.6f, "Resort", "Rubavu",
                "Beautiful lakeside resort with private beach, water sports, tropical gardens, spa, and stunning views across Lake Kivu to the DRC mountains.",
                getDrawableResource("hotel_kivuserena", defaultImage)));

        hotelList.add(new Hotel("Virunga Inn Resort & Spa", 200000, 4.4f, "Resort", "Musanze",
                "Comfortable resort near Volcanoes National Park, featuring spa facilities, restaurant, bar, and ideal base for gorilla trekking adventures.",
                getDrawableResource("hotel_virunga", defaultImage)));

        hotelList.add(new Hotel("Nyungwe Top View Hill Hotel", 187500, 4.3f, "Nature Lodge", "Nyungwe",
                "Eco-friendly lodge overlooking Nyungwe rainforest. Perfect for chimpanzee tracking, canopy walks, and nature enthusiasts seeking authentic experiences.",
                getDrawableResource("hotel_nyungwe", defaultImage)));

        hotelList.add(new Hotel("M Hotel Kigali", 175000, 4.4f, "Mid-Range", "Kigali",
                "Modern mid-range hotel with well-appointed rooms, restaurant serving local and international cuisine, and convenient location near shopping centers.",
                getDrawableResource("hotel_m", defaultImage)));

        hotelList.add(new Hotel("Five to Five Hotel", 100000, 4.4f, "Budget", "Kigali",
                "Excellent value hotel offering clean, comfortable rooms, rooftop restaurant with city views, and friendly hospitality in Kigali's vibrant Kimihurura neighborhood.",
                getDrawableResource("hotel_fivetofive", defaultImage)));

        hotelList.add(new Hotel("Onomo Hotel Kigali", 150000, 4.2f, "Mid-Range", "Kigali",
                "Stylish international chain hotel with contemporary African design, outdoor pool, restaurant, and modern amenities for travelers.",
                getDrawableResource("hotel_onomo", defaultImage)));

        hotelList.add(new Hotel("Hotel des Mille Collines", 250000, 4.6f, "Heritage", "Kigali",
                "Historic landmark hotel featured in 'Hotel Rwanda', offering elegant accommodation, beautiful gardens, pool, and rich cultural heritage.",
                getDrawableResource("hotel_millecollines", defaultImage)));

        hotelList.add(new Hotel("Heaven Boutique Hotel", 162500, 4.7f, "Boutique", "Kigali",
                "Charming boutique hotel supporting local community projects. Features art-filled rooms, farm-fresh restaurant, and warm Rwandan hospitality.",
                getDrawableResource("hotel_heaven", defaultImage)));

        hotelList.add(new Hotel("Ubumwe Grande Hotel", 237500, 4.5f, "Business", "Kigali",
                "Large business hotel with extensive conference facilities, multiple dining options, fitness center, and professional service for corporate travelers.",
                getDrawableResource("hotel_ubumwe", defaultImage)));

        hotelList.add(new Hotel("Lemigo Hotel", 200000, 4.3f, "Business", "Kigali",
                "Modern hotel with rooftop pool and bar, entertainment facilities, business center, and panoramic city views from upper floors.",
                getDrawableResource("hotel_lemigo", defaultImage)));

        hotelList.add(new Hotel("Urban by CityBlue Kigali", 137500, 4.2f, "Mid-Range", "Kigali",
                "Contemporary urban hotel with minimalist design, comfortable rooms, restaurant, and strategic location near business and shopping districts.",
                getDrawableResource("hotel_urban", defaultImage)));

        hotelList.add(new Hotel("Golf Hills Residence", 87500, 4.1f, "Budget", "Kigali",
                "Budget-friendly residence-style accommodation with kitchenettes, near golf course, offering good value for extended stays and families.",
                getDrawableResource("hotel_golfhills", defaultImage)));

        filteredList = new ArrayList<>(hotelList);
    }

    /**
     * Helper method to get drawable resource with fallback
     */
    private int getDrawableResource(String drawableName, int defaultResource) {
        try {
            int resourceId = getResources().getIdentifier(drawableName, "drawable", getPackageName());
            return (resourceId != 0) ? resourceId : defaultResource;
        } catch (Exception e) {
            return defaultResource;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.hotel_list_menu, menu);

        // Setup search with null checks
        MenuItem searchItem = menu.findItem(R.id.action_search);
        if (searchItem != null) {
            SearchView searchView = (SearchView) searchItem.getActionView();
            if (searchView != null) {
                searchView.setQueryHint("Search hotels...");

                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        filterHotels(newText);
                        return true;
                    }
                });
            }
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
            return true;
        } else if (id == R.id.action_sort_price_low) {
            sortByPrice(true);
            return true;
        } else if (id == R.id.action_sort_price_high) {
            sortByPrice(false);
            return true;
        } else if (id == R.id.action_sort_rating) {
            sortByRating();
            return true;
        } else if (id == R.id.action_filter) {
            showFilterDialog();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void filterHotels(String query) {
        filteredList.clear();

        if (query == null || query.isEmpty()) {
            filteredList.addAll(hotelList);
        } else {
            String lowerQuery = query.toLowerCase().trim();
            for (Hotel hotel : hotelList) {
                if (hotel.getName().toLowerCase().contains(lowerQuery) ||
                        hotel.getLocation().toLowerCase().contains(lowerQuery) ||
                        hotel.getCategory().toLowerCase().contains(lowerQuery)) {
                    filteredList.add(hotel);
                }
            }
        }

        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

    private void sortByPrice(boolean ascending) {
        Collections.sort(filteredList, new Comparator<Hotel>() {
            @Override
            public int compare(Hotel h1, Hotel h2) {
                return ascending ?
                        Integer.compare(h1.getPrice(), h2.getPrice()) :
                        Integer.compare(h2.getPrice(), h1.getPrice());
            }
        });

        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }

        String direction = ascending ? "Low to High" : "High to Low";
        Toast.makeText(this, "Sorted by price (" + direction + ")", Toast.LENGTH_SHORT).show();
    }

    private void sortByRating() {
        Collections.sort(filteredList, new Comparator<Hotel>() {
            @Override
            public int compare(Hotel h1, Hotel h2) {
                return Float.compare(h2.getRating(), h1.getRating());
            }
        });

        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }

        Toast.makeText(this, "Sorted by rating (Highest first)", Toast.LENGTH_SHORT).show();
    }

    private void showFilterDialog() {
        // Check if activity is still valid
        if (isFinishing() || isDestroyed()) {
            return;
        }

        String[] categories = {"All", "Luxury", "Business", "Heritage", "Boutique", "Resort", "Safari Luxury", "Budget", "Mid-Range", "Nature Lodge"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Filter by Category")
                .setItems(categories, (dialog, which) -> {
                    filterByCategory(categories[which]);
                })
                .show();
    }

    private void filterByCategory(String category) {
        filteredList.clear();

        if (category.equals("All")) {
            filteredList.addAll(hotelList);
        } else {
            for (Hotel hotel : hotelList) {
                if (hotel.getCategory().equals(category)) {
                    filteredList.add(hotel);
                }
            }
        }

        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }

        Toast.makeText(this, "Filtered by: " + category, Toast.LENGTH_SHORT).show();
    }

    private void showHotelDetails(Hotel hotel) {
        // Check if activity is still valid
        if (isFinishing() || isDestroyed()) {
            return;
        }

        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            View dialogView = getLayoutInflater().inflate(R.layout.dialog_hotel_details, null);

            ImageView hotelImage = dialogView.findViewById(R.id.detailHotelImage);
            TextView nameText = dialogView.findViewById(R.id.detailHotelName);
            TextView priceText = dialogView.findViewById(R.id.detailHotelPrice);
            TextView locationText = dialogView.findViewById(R.id.detailHotelLocation);
            TextView categoryText = dialogView.findViewById(R.id.detailHotelCategory);
            TextView descriptionText = dialogView.findViewById(R.id.detailHotelDescription);
            RatingBar ratingBar = dialogView.findViewById(R.id.detailHotelRating);
            Button bookButton = dialogView.findViewById(R.id.btnBookNow);

            if (hotelImage != null) hotelImage.setImageResource(hotel.getImageResource());
            if (nameText != null) nameText.setText(hotel.getName());
            if (priceText != null) priceText.setText(formatPrice(hotel.getPrice()) + "/night");
            if (locationText != null) locationText.setText("Location: " + hotel.getLocation());
            if (categoryText != null) categoryText.setText("Category: " + hotel.getCategory());
            if (descriptionText != null) descriptionText.setText(hotel.getDescription());
            if (ratingBar != null) ratingBar.setRating(hotel.getRating());

            AlertDialog dialog = builder.setView(dialogView)
                    .setNegativeButton("Close", null)
                    .create();

            if (bookButton != null) {
                bookButton.setOnClickListener(v -> {
                    bookHotel(hotel);
                    if (dialog != null && dialog.isShowing()) {
                        dialog.dismiss();
                    }
                });
            }

            dialog.show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error showing hotel details", Toast.LENGTH_SHORT).show();
        }
    }

    private void bookHotel(Hotel hotel) {
        // Check if activity is still valid
        if (isFinishing() || isDestroyed()) {
            return;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm Booking")
                .setMessage("Book " + hotel.getName() + " for " + formatPrice(hotel.getPrice()) + "/night?")
                .setPositiveButton("Confirm", (dialog, which) -> {
                    Toast.makeText(this, "Booking confirmed for " + hotel.getName(),
                            Toast.LENGTH_LONG).show();
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    private String formatPrice(int price) {
        NumberFormat formatter = NumberFormat.getInstance(Locale.US);
        return "FRW " + formatter.format(price);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Clean up to prevent memory leaks
        if (adapter != null) {
            adapter = null;
        }
        if (filteredList != null) {
            filteredList.clear();
            filteredList = null;
        }
        if (hotelList != null) {
            hotelList.clear();
            hotelList = null;
        }
    }

    // Custom Adapter with ViewHolder pattern for better performance
    private class HotelAdapter extends ArrayAdapter<Hotel> {

        public HotelAdapter(List<Hotel> hotels) {
            super(HotelListActivity.this, 0, hotels);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            ViewHolder holder;
            Hotel hotel = getItem(position);

            if (hotel == null) {
                return convertView != null ? convertView : new View(getContext());
            }

            if (convertView == null) {
                convertView = LayoutInflater.from(getContext())
                        .inflate(R.layout.hotel_list_item, parent, false);

                holder = new ViewHolder();
                holder.hotelImage = convertView.findViewById(R.id.hotelImage);
                holder.nameText = convertView.findViewById(R.id.hotelName);
                holder.priceText = convertView.findViewById(R.id.hotelPrice);
                holder.locationText = convertView.findViewById(R.id.hotelLocation);
                holder.categoryText = convertView.findViewById(R.id.hotelCategory);
                holder.ratingBar = convertView.findViewById(R.id.hotelRating);
                holder.favoriteIcon = convertView.findViewById(R.id.favoriteIcon);
                holder.detailsButton = convertView.findViewById(R.id.btnDetails);
                holder.bookButton = convertView.findViewById(R.id.btnQuickBook);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            // Set data with null checks
            if (holder.hotelImage != null) {
                holder.hotelImage.setImageResource(hotel.getImageResource());
            }
            if (holder.nameText != null) {
                holder.nameText.setText(hotel.getName());
            }
            if (holder.priceText != null) {
                holder.priceText.setText(formatPrice(hotel.getPrice()) + "/night");
            }
            if (holder.locationText != null) {
                holder.locationText.setText(hotel.getLocation());
            }
            if (holder.categoryText != null) {
                holder.categoryText.setText(hotel.getCategory());
            }
            if (holder.ratingBar != null) {
                holder.ratingBar.setRating(hotel.getRating());
            }

            // Favorite toggle
            if (holder.favoriteIcon != null) {
                holder.favoriteIcon.setImageResource(
                        hotel.isFavorite() ? android.R.drawable.star_big_on : android.R.drawable.star_big_off
                );

                holder.favoriteIcon.setOnClickListener(v -> {
                    hotel.setFavorite(!hotel.isFavorite());
                    notifyDataSetChanged();
                    Toast.makeText(getContext(),
                            hotel.isFavorite() ? "Added to favorites" : "Removed from favorites",
                            Toast.LENGTH_SHORT).show();
                });
            }

            // Details button
            if (holder.detailsButton != null) {
                holder.detailsButton.setOnClickListener(v -> showHotelDetails(hotel));
            }

            // Quick book button
            if (holder.bookButton != null) {
                holder.bookButton.setOnClickListener(v -> bookHotel(hotel));
            }

            return convertView;
        }

        // ViewHolder pattern to improve performance
        private class ViewHolder {
            ImageView hotelImage;
            TextView nameText;
            TextView priceText;
            TextView locationText;
            TextView categoryText;
            RatingBar ratingBar;
            ImageView favoriteIcon;
            Button detailsButton;
            Button bookButton;
        }
    }

    // Hotel Model Class
    private static class Hotel {
        private final String name;
        private final int price;
        private final float rating;
        private final String category;
        private final String location;
        private final String description;
        private final int imageResource;
        private boolean favorite;

        public Hotel(String name, int price, float rating, String category, String location,
                     String description, int imageResource) {
            this.name = name;
            this.price = price;
            this.rating = rating;
            this.category = category;
            this.location = location;
            this.description = description;
            this.imageResource = imageResource;
            this.favorite = false;
        }

        public String getName() { return name; }
        public int getPrice() { return price; }
        public float getRating() { return rating; }
        public String getCategory() { return category; }
        public String getLocation() { return location; }
        public String getDescription() { return description; }
        public int getImageResource() { return imageResource; }
        public boolean isFavorite() { return favorite; }
        public void setFavorite(boolean favorite) { this.favorite = favorite; }
    }
}