import { React } from 'react';
import { Link } from 'react-router-dom';

import '../css/YearSelector.scss';

export const YearSelector = ({teamName}) => {
    
    let years = [];
    const startYear = process.env.REACT_APP_DATA_START_YEAR;
    const endYear = process.env.REACT_APP_DATA_END_YEAR;

    for (let i = startYear; i <= endYear; i++ ) {
        years.push(i);
    }

    return (
        <ol className="YearSelector">
        { years.map(year => (
            <li key={year}>
                <Link to={{pathname: '/dashboard/matchDetails',
                            search: `?teamName=${teamName}&year=${year}`}}>
                        {year}
                </Link>
            </li>
        )) }
        </ol>
    );

}