import './App.css';
import SiteHeader from './components/SiteHeader';
import Forum from './components/Forum';
import ForumHeader from './components/ForumHeader';

function App() {
  return (
      <div className="App">
        <header className="App-header">
          <SiteHeader />
        </header>
        <div className="Content">
          <ForumHeader />
          <Forum />
        </div>
      </div>
  );
}
export default App;
