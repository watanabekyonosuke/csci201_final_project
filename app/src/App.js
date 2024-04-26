import './App.css';
import { ReactDOM } from 'react';
import {BrowserRouter, Routes, Route } from "react-router-dom";
import SiteHeader from './components/SiteHeader';
import Landing from './components/Landing';
import ForumList from './components/ForumList';
import Discussion from './components/Discussion';
import SignIn from './components/SignIn';
import SignUp from './components/SignUp';
import CreatePost from './components/CreatePost';

function App() {
  return (
    <BrowserRouter>
      <div className="App">
        <header className="App-header">
          <SiteHeader />
        </header>
        <Routes>
          <Route path="/" element={<Landing />} />
          <Route path="SignIn" element={<SignIn />} />
          <Route path="SignUp" element={<SignUp />} />
          <Route path="ForumList" element={<ForumList />}/>
          <Route path="Discussion" element={<Discussion />}/>
          <Route path="Landing" element={<Landing />}/>
          <Route path="CreatePost" element={<CreatePost />}/>
        </Routes>
      </div>
    </BrowserRouter>
  );
}
export default App;
