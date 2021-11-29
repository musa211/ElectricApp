# Electric App

Electric App is a mobile application made on Android Studio
that utilized the National Renewable Energy Laboratory 
(PV Watts) API to detect nearby utility companies and 
electricity rates.

## Authors

- [@musajean](https://github.com/musa211)

## Demo

Will insert soon, my AVD is lagging at the moment.

## Acknowledgements

 - [Awesome Icons](https://iconscout.com)
 - [FIU GDSC](https://gdsc.community.dev/florida-international-university/)
 - [How To Implement Firebase](https://youtu.be/DZ60-GvGwn0)
 - [NREL Utility Rates API](https://developer.nrel.gov/docs/electricity/)

## Features

- Firebase Authentication
- Usage of NREL API
- Works Across Locations in U.S

## API Reference

#### Requesr URL

```http
  GET /api/utility_rates/v3.format?parameters
```

| Parameter | Required  |  Type     | Description                |
| :-------- | :-------- | :-------- | :------------------------- |
| `format`  | `Yes`     | `string`  | The output response format |
| `api_key` | `Yes`     | `string`  | Your API key |
| `address` | `Depends` | `string`  | The address to use. Required if lat/lon not provided. |
| `lat`     | `Depends` | `decimal` | The latitude for the location to use. Required if address not given. |
| `lon`     | `Depends` | `decimal` | The longitude for the location to use. Required if address not given. |
| `radius`  | `No`      | `decimal` | The radius (in miles) around the search location to search for utility rates with intersecting boundaries. With the default radius of 0, only utility rates whose boundaries contain the search location point will be returned. |
| `limit`   | `No`      | `decimal` | The maximum number of results to return. If no limit is specified then all matching results will be returned. |

#### Response Fileds

| Field          | Value        | Description                |
| :------------- | :----------- | :------------------------- |
| `utility_name` | `string`     | The name of the utility company. If there are multiple utility companies serving the location, the names will be returned as a pipe-delimited string. |
| `company_id`   | `integer`    | The ID of the utility company. If there are multiple utility companies serving the location, the IDs will be returned as a pipe-delimited string. |
| `utility_info` | `collection` | An array of hashes containing the name(s) and ID(s) of the utility company or companies serving the location. |
| `residential`  | `decimal`    | The residential electricity rate ($/kWh). |
| `commercial`   | `decimal`    | The commercial electricity rate ($/kWh). |
| `industrial`   | `decimal`    | The industrial electricity rate ($/kWh). |


## Environment Variables

To run this project, you will need to add the following environment variables to your UtilityCompanyActivity.java & UtilityRateActivity.java file

`API_KEY`

From [API Key Usage](https://developer.nrel.gov/docs/api-key/)

## Run Locally

Clone the project

```bash
  git clone https://github.com/musa211/ElectricApp
```

Open with Android Studio

Run using your AVD (virtual device)

Start the server


## Documentation

[Async Http Client](https://guides.codepath.com/android/Using-CodePath-Async-Http-Client)

[Recycler View](https://guides.codepath.com/android/using-the-recyclerview#overviews)

## Lessons Learned

#### What did you learn while building this project? What challenges did you face and how did you overcome them?

This is my first significant project utilizing Android Studio. I learned how to implement 
- Firebase Authentication
- Sending JSON Request
- Recycler View & Adapter
- Email Validation Using Regex
- Interactive UI
- Activities & Intents
- Clickable Images
- Input Controls
- Menus and Pickers
- User Navigation

I faced issue with handling the JSON output file and working with the different layouts.
JSON issue was overcomed through appropriate logging. I just used trial and error for issues with the layouts.


## FAQ

#### Do I need an API Key in order to use this application?

Yes, but I provided a default key to use but feel free create your own.

#### What IDE to I use to run this application?

Android Studio, and make sure to install an AVD.
## Badges

Add badges from somewhere like: [shields.io](https://shields.io/)

![Language](https://img.shields.io/github/languages/top/musa211/ElectricApp?style=plastic)

![Line of Code](https://img.shields.io/tokei/lines/github/musa211/ElectricApp)

![Repo Size](https://img.shields.io/github/repo-size/musa211/ElectricApp)

![Commit Activity](https://img.shields.io/github/commit-activity/m/musa211/ElectricApp)

![Contributors](https://img.shields.io/github/contributors/musa211/ElectricApp?color=blue)

