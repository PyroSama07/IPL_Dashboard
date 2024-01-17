import './css/App.css';
import {React} from 'react';
import {HashRouter as Router, Route, Routes} from 'react-router-dom';
import { TeamPage } from './pages/TeamPage';
import { MatchPage } from './pages/MatchPage';
import { HomePage } from './pages/HomePage';

function App (){
    return (
        <div className='App'>
            <Router>
                <Routes>
                    <Route path="/" element={<HomePage />}></Route>
                    <Route path="/dashboard/teamDetails" element={<TeamPage />}></Route>
                    <Route path="/dashboard/matchDetails" element={<MatchPage />}></Route>
                </Routes>
            </Router>
        </div>
    );
}

export default App;
